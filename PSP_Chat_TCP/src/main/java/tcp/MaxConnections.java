package tcp;

public class MaxConnections extends Exception {
	private static final long serialVersionUID = -1002766384869121579L;

	public String getMessage() {
		return "Se ha alcanzado el n�mero m�ximo de conexiones";
	}
}
