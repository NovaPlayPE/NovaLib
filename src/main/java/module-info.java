module net.novateech.library {
	exports net.novatech.library.reflection;
	exports net.novatech.library.nbt.tags;
	exports net.novatech.library.noise;
	exports net.novatech.library.nbt;
	exports net.novatech.library.utils;
	exports net.novatech.library.math;
	exports net.novatech.library.physics;
	exports net.novatech.library.callback;

	requires com.github.luben.zstd_jni;
	requires guava;
	requires io.netty.buffer;
	requires io.netty.common;
	requires java.management;
	requires lombok;
	requires org.apache.commons.compress;
}