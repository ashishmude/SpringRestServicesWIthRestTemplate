package nl.bb.openb.dto;

/**
 * @author Ashish_Mude
 *
 */
public class RequestDTO<T> {
	private T payload;

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}
	
}
