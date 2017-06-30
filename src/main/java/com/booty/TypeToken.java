package com.booty;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeToken {
	
	static class TypesafeMap {
		Map<Type, Object> safeMap = new HashMap<>();
		
		<T> void put(TypeReference<T> tr, T value){
			safeMap.put(tr.type, value);
		}
		
		<T> T get(TypeReference<T> tr){
			return tr.type instanceof Class<?> ? 
					((Class<T>)tr.type).cast(safeMap.get(tr.type))
					:
					((Class<T>)((ParameterizedType)tr.type).getRawType()).cast(safeMap.get(tr.type));
		}
	}
	
	
	static class TypeReference<T> {
		Type type;
		
		public TypeReference(){
			Type stype = getClass().getGenericSuperclass();
			if(stype instanceof ParameterizedType){
				this.type = ((ParameterizedType)stype).getActualTypeArguments()[0];
			}else throw new RuntimeException();
		}
	}

	//Super Type Token
	public static void main(String[] args) {
		TypesafeMap s = new TypesafeMap();
		
		s.put(new TypeReference<String>(){}, "String");
		s.put(new TypeReference<List<String>>(){}, Arrays.asList("1", "2"));
		s.put(new TypeReference<List<Integer>>(){}, Arrays.asList(10, 20));
		
		System.out.println(s.get(new TypeReference<String>(){}));
		System.out.println(s.get(new TypeReference<List<String>>(){}));
		System.out.println(s.get(new TypeReference<List<Integer>>(){}));
	}
}
