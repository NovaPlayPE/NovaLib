package net.novatech.library.utils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ZstdUtils {
	
	/**
	 * 
	 * @param Directory path
	 * @throws IOException
	 * 
	 * returns .zstd file
	 * @throws CompressorException 
	 */
	public static void compress(String path, String directory) throws IOException, CompressorException{
		Path src = Paths.get(path + directory);
		createTarGzipFolder(src);
		
		String pathNew = path + directory + ".tar.gz";
		File input = new File(pathNew);
		final File output = new File(path + ".zstd");
		try(FileInputStream is = new FileInputStream(input);
				FileOutputStream os = new FileOutputStream(output);
				CompressorOutputStream zos = new CompressorStreamFactory().createCompressorOutputStream("zstd", os)){
			IOUtils.copy(is, zos);
		}
		try(FileInputStream in = new FileInputStream(input);
				CompressorInputStream zis = new CompressorStreamFactory().createCompressorInputStream("zstd", new FileInputStream(output))){
			byte[] expected = IOUtils.toByteArray(in);
			byte[] actual = IOUtils.toByteArray(zis);
		}
	}
	
	/**
	 * 
	 * @param .zstd file path
	 * @throws IOException
	 * @throws CompressorException 
	 */
	public static void decompress(String path, String fileName, String directoryOut) throws IOException, CompressorException{
		final File input = new File(path + fileName + ".zstd");
		final File output = new File(path + fileName + ".tar.gz");
		try(InputStream is = new FileInputStream(input)){
			final CompressorInputStream in = new CompressorStreamFactory().createCompressorInputStream("zstd", is);
			FileOutputStream out = null;
			try{
				out = new FileOutputStream(output);
				IOUtils.copy(in, out);
			} finally {
				if(out != null) {
					out.close();
				}
				in.close();
			}
		}
		decompressTarGzipFile(Paths.get(path + fileName + ".tar.gzip"), Paths.get(path + directoryOut));
		
	}
	
	

	@SuppressWarnings("unchecked")
	public static void createTarGzipFolder(Path source) throws IOException {
		if (!Files.isDirectory(source)) {
			throw new IOException("Please provide a directory.");
		}
		String tarFileName = source.getFileName().toString() + ".tar.gz";
		try (OutputStream fOut = Files.newOutputStream(Paths.get(tarFileName));
				BufferedOutputStream buffOut = new BufferedOutputStream(fOut);
				GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(buffOut);
				TarArchiveOutputStream tOut = new TarArchiveOutputStream(gzOut)) {
			Files.walkFileTree(source, new SimpleFileVisitor() {
				public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
					if (attributes.isSymbolicLink()) {
						return FileVisitResult.CONTINUE;
					}
					Path targetFile = source.relativize(file);
					try {
						TarArchiveEntry tarEntry = new TarArchiveEntry(file.toFile(), targetFile.toString());
						tOut.putArchiveEntry(tarEntry);
						Files.copy(file, tOut);
						tOut.closeArchiveEntry();
						System.out.printf("file : %s%n", file);
					} catch (IOException e) {
						System.err.printf("Unable to tar.gz : %s%n%s%n", file, e);
					}
					return FileVisitResult.CONTINUE;
				}
				public FileVisitResult visitFileFailed(Path file, IOException exc) {
					System.err.printf("Unable to tar.gz : %s%n%s%n", file, exc);
					return FileVisitResult.CONTINUE;
				}
			});
			tOut.finish();
		}
	}

	public static void decompressTarGzipFile(Path source, Path target) throws IOException {
		if (Files.notExists(source)) {
			throw new IOException("File doesn't exists!");
		}
		try (InputStream fi = Files.newInputStream(source);
				BufferedInputStream bi = new BufferedInputStream(fi);
				GzipCompressorInputStream gzi = new GzipCompressorInputStream(bi);
				TarArchiveInputStream ti = new TarArchiveInputStream(gzi)) {
			ArchiveEntry entry;
			while ((entry = ti.getNextEntry()) != null) {
				Path newPath = zipSlipProtect(entry, target);
				if (entry.isDirectory()) {
					Files.createDirectories(newPath);
				} else {
					Path parent = newPath.getParent();
					if (parent != null) {
						if (Files.notExists(parent)) {
							Files.createDirectories(parent);
						}
					}

					Files.copy(ti, newPath, StandardCopyOption.REPLACE_EXISTING);

				}
			}
		}
	}

	private static Path zipSlipProtect(ArchiveEntry entry, Path targetDir) throws IOException {

		Path targetDirResolved = targetDir.resolve(entry.getName());
		Path normalizePath = targetDirResolved.normalize();

		if (!normalizePath.startsWith(targetDir)) {
			throw new IOException("Bad entry: " + entry.getName());
		}

		return normalizePath;
	}

}