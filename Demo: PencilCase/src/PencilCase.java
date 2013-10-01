
public class PencilCase {
	private Pencil[] pencils;
	
	/**
	 * Default Constructor
	 */
	public PencilCase() {
		int i;
		
		pencils = new Pencil[10];
		
		for (i = 0; i < 10; i++) {
			pencils[i] = new Pencil(10, true);
		}
	}

	/**
	 * Copy Constructor
	 * @param other
	 */
	public PencilCase(PencilCase other) {
		// FIXME: there is aliasing going on here!
		this.pencils = other.pencils;
	}
	
	public Pencil getPencil(int i) {
		if (i >= 0 && i < 10) {
			return pencils[i];
		} else {
			return null;
		}
 	}
}
