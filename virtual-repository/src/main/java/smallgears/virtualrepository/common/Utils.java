package smallgears.virtualrepository.common;

import static smallgears.virtualrepository.AssetType.*;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import smallgears.virtualrepository.AssetType;

@UtilityClass
public class Utils {
	
	
	/**
	 * <code>true</code> if a given type is the same or a subtype of another.
	 */
	public boolean ordered(@NonNull AssetType t1,  @NonNull AssetType t2) {
		
		return t1.equals(t2) || t2 == any || t1.specialises().contains(t2);
	};
	
	/**
	 * <code>true</code> if a given api is the same or a subtype of another.
	 */
	public boolean ordered(@NonNull Class<?> t1,  @NonNull Class<?> t2) {
		
		return t1.equals(t2) || t2.isAssignableFrom(t1);
	};
}