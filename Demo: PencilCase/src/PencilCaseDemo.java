
public class PencilCaseDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pencil yellowNumber2 = new Pencil();
		Pencil noveltyTrollDollPencil = new Pencil(43);
		yellowNumber2.sharpen();
		noveltyTrollDollPencil.drawALine();
		
		PencilCase myPencils = new PencilCase();
		Pencil myFavorite = myPencils.getPencil(7);
	}
}
