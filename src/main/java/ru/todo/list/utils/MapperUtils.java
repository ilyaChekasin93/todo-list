package ru.todo.list.utils;

import org.mapstruct.Mapper;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.todo.list.constant.AppConst.APP_ROOT_PATH;

public class MapperUtils {

    public static final String IMPL_STRING = "Impl";

    public static Map<Class, Class> getMapperTypes() {
        Set<Class<?>> allMapperType = getAllMapperType();
        Set<Class<?>> allMapperInterfaceType = findAllMapperInterfaceType(allMapperType);
        Set<Class<?>> allMapperImplType = findAllMapperImplType(allMapperType);

        return allMapperInterfaceType.stream()
                .collect(Collectors.toMap(
                        m -> m,
                        m -> findMapperImplType(m, allMapperImplType)
                ));
    }

    private static Set<Class<?>> findAllMapperInterfaceType(Set<Class<?>> allMapperType) {
        return allMapperType.stream().filter(m -> isInterface(m)).collect(Collectors.toSet());
    }

    private static Set<Class<?>> findAllMapperImplType(Set<Class<?>> allMapperType) {
        return allMapperType.stream().filter(m -> !isInterface(m)).collect(Collectors.toSet());
    }

    private static Set<Class<?>> getAllMapperType() {
        Reflections reflections = new Reflections(APP_ROOT_PATH);

        return reflections.getTypesAnnotatedWith(Mapper.class);
    }

    private static Class findMapperImplType(Class<?> interfaceMapperType, Set<Class<?>> allMapperImplType) {
        return allMapperImplType.stream()
                .filter(m -> m.getSimpleName().equals(interfaceMapperType.getSimpleName() + IMPL_STRING))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
    }

    private static boolean isInterface(Class clazz) {
        return clazz.getConstructors().length == 0;
    }

}
