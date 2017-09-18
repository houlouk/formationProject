package nonPojoModel;

import java.util.ArrayList;
import java.util.List;

public class EmploiDuTemps<T> {
	
	private T base;
	private List<CreneauEmploiDuTemps> emploiDuTemps = new ArrayList<>();

	public T getBase() {
		return base;
	}
	public void setBase(T base) {
		this.base = base;
	}
	public List<CreneauEmploiDuTemps> getEmploiDuTemps() {
		return emploiDuTemps;
	}
	public void addCreneau(CreneauEmploiDuTemps creneau) {
		this.emploiDuTemps.add(creneau);
	}

}
