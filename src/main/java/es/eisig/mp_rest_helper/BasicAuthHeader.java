package es.eisig.mp_rest_helper;

import lombok.*;

import javax.ws.rs.HeaderParam;
import java.util.Base64;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class BasicAuthHeader {
	@HeaderParam("Authorization")
	String authHeader;

	public static class BasicAuthHeaderBuilder {
		private String username;
		private String password;

		public BasicAuthHeaderBuilder username(String username) {
			this.username = username;
			this.authHeader = computeHeader(this.username, this.password);
			return this;
		}
		public BasicAuthHeaderBuilder password(String password) {
			this.password = password;
			this.authHeader = computeHeader(this.username, this.password);
			return this;
		}

		BasicAuthHeaderBuilder authHeader(String authHeader) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Cannot manually set authHeader");
		}

		String computeHeader(String username, String password) {
			if (username == null && password == null) return null;
			return String.format("Basic %s",
					Base64.getUrlEncoder().encodeToString(
							String.format("%s:%s", username, password).getBytes()
					)
			);
		}
	}
}