package net.novatech.library.reflection;


import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Reflect {
	
    private final Object object;
    private final boolean isClass;

    private Reflect(Class<?> type) {
        this.object = type;
        this.isClass = true;
    }

    private Reflect(Object object) {
        this.object = object;
        this.isClass = false;
    }

    public static Reflect on(String name) throws ReflectException {
        return Reflect.on(Reflect.forName(name));
    }

    public static Reflect on(String name, ClassLoader classLoader) throws ReflectException {
        return Reflect.on(Reflect.forName(name, classLoader));
    }

    public static Reflect on(Class<?> clazz) {
        return new Reflect(clazz);
    }

    public static Reflect on(Object object) {
        return new Reflect(object);
    }

    public static <T extends AccessibleObject> T accessible(T accessible) {
        Member member;
        if (accessible == null) {
            return null;
        }
        if (accessible instanceof Member && Modifier.isPublic((member = (Member)accessible).getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
            return accessible;
        }
        if (!accessible.isAccessible()) {
            accessible.setAccessible(true);
        }
        return accessible;
    }

    private static String property(String string) {
        int length = string.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return string.toLowerCase();
        }
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }

    private static /* varargs */ Reflect on(Constructor<?> constructor, Object ... args) throws ReflectException {
        try {
            return Reflect.on(Reflect.accessible(constructor).newInstance(args));
        }
        catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static /* varargs */ Reflect on(Method method, Object object, Object ... args) throws ReflectException {
        try {
            Reflect.accessible(method);
            if (method.getReturnType() == Void.TYPE) {
                method.invoke(object, args);
                return Reflect.on(object);
            }
            return Reflect.on(method.invoke(object, args));
        }
        catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Object unwrap(Object object) {
        if (object instanceof Reflect) {
            return ((Reflect)object).get();
        }
        return object;
    }

    private static /* varargs */ Class<?>[] types(Object ... values) {
        if (values == null) {
            return new Class[0];
        }
        Class[] result = new Class[values.length];
        for (int i = 0; i < values.length; ++i) {
            Object value = values[i];
            result[i] = value == null ? NULL.class : value.getClass();
        }
        return result;
    }

    private static Class<?> forName(String name) throws ReflectException {
        try {
            return Class.forName(name);
        }
        catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private static Class<?> forName(String name, ClassLoader classLoader) throws ReflectException {
        try {
            return Class.forName(name, true, classLoader);
        }
        catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public static Class<?> wrapper(Class<?> type) {
        if (type == null) {
            return null;
        }
        if (type.isPrimitive()) {
            if (Boolean.TYPE == type) {
                return Boolean.class;
            }
            if (Integer.TYPE == type) {
                return Integer.class;
            }
            if (Long.TYPE == type) {
                return Long.class;
            }
            if (Short.TYPE == type) {
                return Short.class;
            }
            if (Byte.TYPE == type) {
                return Byte.class;
            }
            if (Double.TYPE == type) {
                return Double.class;
            }
            if (Float.TYPE == type) {
                return Float.class;
            }
            if (Character.TYPE == type) {
                return Character.class;
            }
            if (Void.TYPE == type) {
                return Void.class;
            }
        }
        return type;
    }

    public <T> T get() {
        return (T)this.object;
    }

    public Reflect set(String name, Object value) throws ReflectException {
        try {
            Field field = this.field0(name);
            if ((field.getModifiers() & 16) == 16) {
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, field.getModifiers() & -17);
            }
            field.set(this.object, Reflect.unwrap(value));
            return this;
        }
        catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    public <T> T get(String name) throws ReflectException {
        return this.field(name).get();
    }

    public Reflect field(String name) throws ReflectException {
        try {
            Field field = this.field0(name);
            return Reflect.on(field.get(this.object));
        }
        catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    private Field field0(String name) throws ReflectException {
        Class<?> type = this.type();
        try {
            return Reflect.accessible(type.getField(name));
        }
        catch (NoSuchFieldException e) {
            do {
                try {
                    return Reflect.accessible(type.getDeclaredField(name));
                }
                catch (NoSuchFieldException noSuchFieldException) {
                    if ((type = type.getSuperclass()) != null) continue;
                    throw new ReflectException(e);
                }
            } while (true);
        }
    }

    public Map<String, Reflect> fields() {
        LinkedHashMap<String, Reflect> result = new LinkedHashMap<String, Reflect>();
        Class<?> type = this.type();
        do {
            for (Field field : type.getDeclaredFields()) {
                String name;
                if (!(!this.isClass ^ Modifier.isStatic(field.getModifiers())) || result.containsKey(name = field.getName())) continue;
                result.put(name, this.field(name));
            }
        } while ((type = type.getSuperclass()) != null);
        return result;
    }

    public Reflect call(String name) throws ReflectException {
        return this.call(name, new Object[0]);
    }

    public /* varargs */ Reflect call(String name, Object ... args) throws ReflectException {
        Class<?>[] types = Reflect.types(args);
        try {
            Method method = this.exactMethod(name, types);
            return Reflect.on(method, this.object, args);
        }
        catch (NoSuchMethodException e) {
            try {
                Method method = this.similarMethod(name, types);
                return Reflect.on(method, this.object, args);
            }
            catch (NoSuchMethodException e1) {
                throw new ReflectException(e1);
            }
        }
    }

    private Method exactMethod(String name, Class<?>[] types) throws NoSuchMethodException {
        Class<?> type = this.type();
        try {
            return type.getMethod(name, types);
        }
        catch (NoSuchMethodException e) {
            do {
                try {
                    return type.getDeclaredMethod(name, types);
                }
                catch (NoSuchMethodException noSuchMethodException) {
                    if ((type = type.getSuperclass()) != null) continue;
                    throw new NoSuchMethodException();
                }
            } while (true);
        }
    }

    private Method similarMethod(String name, Class<?>[] types) throws NoSuchMethodException {
        Class<?> type = this.type();
        for (Method method : type.getMethods()) {
            if (!this.isSimilarSignature(method, name, types)) continue;
            return method;
        }
        do {
            for (Method method : type.getDeclaredMethods()) {
                if (!this.isSimilarSignature(method, name, types)) continue;
                return method;
            }
        } while ((type = type.getSuperclass()) != null);
        throw new NoSuchMethodException("No similar method " + name + " with params " + Arrays.toString(types) + " could be found on type " + this.type() + ".");
    }

    private boolean isSimilarSignature(Method possiblyMatchingMethod, String desiredMethodName, Class<?>[] desiredParamTypes) {
        return possiblyMatchingMethod.getName().equals(desiredMethodName) && this.match(possiblyMatchingMethod.getParameterTypes(), desiredParamTypes);
    }

    public Reflect create() throws ReflectException {
        return this.create(new Object[0]);
    }

    public /* varargs */ Reflect create(Object ... args) throws ReflectException {
        Class<?>[] types = Reflect.types(args);
        try {
            Constructor<?> constructor = this.type().getDeclaredConstructor(types);
            return Reflect.on(constructor, args);
        }
        catch (NoSuchMethodException e) {
            for (Constructor<?> constructor : this.type().getDeclaredConstructors()) {
                if (!this.match(constructor.getParameterTypes(), types)) continue;
                return Reflect.on(constructor, args);
            }
            throw new ReflectException(e);
        }
    }
    
    public <T> T newInstance() {
    	return this.newInstance(new Object[0]);
    }
    
    public <T> T newInstance(Object... args) {
    	if(!this.isClass) throw new ReflectException("This object isn't class");
    	Reflect r = create(args);
    	return r.get();
    }

    public <P> P as(Class<P> proxyType) {
        final boolean isMap = this.object instanceof Map;
        InvocationHandler handler = new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = method.getName();
                try {
                    return Reflect.on(Reflect.this.object).call(name, args).get();
                }
                catch (ReflectException e) {
                    if (isMap) {
                        int length;
                        Map map = (Map)Reflect.this.object;
                        int n = length = args == null ? 0 : args.length;
                        if (length == 0 && name.startsWith("get")) {
                            return map.get(Reflect.property(name.substring(3)));
                        }
                        if (length == 0 && name.startsWith("is")) {
                            return map.get(Reflect.property(name.substring(2)));
                        }
                        if (length == 1 && name.startsWith("set")) {
                            map.put(Reflect.property(name.substring(3)), args[0]);
                            return null;
                        }
                    }
                    throw e;
                }
            }
        };
        return (P)Proxy.newProxyInstance(proxyType.getClassLoader(), new Class[]{proxyType}, handler);
    }

    private boolean match(Class<?>[] declaredTypes, Class<?>[] actualTypes) {
        if (declaredTypes.length == actualTypes.length) {
            for (int i = 0; i < actualTypes.length; ++i) {
                if (actualTypes[i] == NULL.class || Reflect.wrapper(declaredTypes[i]).isAssignableFrom(Reflect.wrapper(actualTypes[i]))) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.object.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Reflect) {
            return this.object.equals(((Reflect)obj).get());
        }
        return false;
    }

    public String toString() {
        return this.object.toString();
    }

    public Class<?> type() {
        if (this.isClass) {
            return (Class)this.object;
        }
        return this.object.getClass();
    }

    private static class NULL {
        private NULL() {
        }
    }

}