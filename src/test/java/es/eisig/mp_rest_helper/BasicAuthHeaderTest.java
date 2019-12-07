package es.eisig.mp_rest_helper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAuthHeaderTest {
	@Test
	@DisplayName("auth header cannot be set in builder")
	public void builderDisablesAuthHeader() {
		UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> BasicAuthHeader.builder().authHeader("set here"));

		assertEquals("Cannot manually set authHeader", ex.getMessage(), "message");
	}

	@Test
	@DisplayName("auth header value should be generated properly")
	public void generatesProperAuthString() {
		assertEquals("Basic dXNlcjpwYXNz", BasicAuthHeader.builder().computeHeader("user", "pass"));
	}

	@Test
	@DisplayName("auth header value should be null if username or password have not been set")
	public void generatesNullWhenLackingInput() {
		assertEquals(null, BasicAuthHeader.builder().build().getAuthHeader(), "authHeader should be null");

		assertEquals(null, BasicAuthHeader.builder().username("user").build().getAuthHeader(), "authHeader should be null if password not set");

		assertEquals(null, BasicAuthHeader.builder().password("pass").build().getAuthHeader(), "authHeader should be null if username not set");
	}

	@Test
	@DisplayName("")
	public void generatesAuthStringFromBuilder() {
		assertEquals("Basic dXNlcjpwYXNz", BasicAuthHeader.builder()
				.username("user")
				.password("pass")
				.build().getAuthHeader());
	}
}
