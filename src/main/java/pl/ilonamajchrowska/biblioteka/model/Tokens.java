	package pl.ilonamajchrowska.biblioteka.model;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.Id;
	import javax.persistence.Table;
	import javax.persistence.Transient;
	import javax.validation.constraints.NotNull;

	import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

	@Entity
	@Table(name = "tokens")
	public class Tokens {
		
		@Id
		@Column(name = "email", unique = true, 
				nullable = false, length = 45)
		@NotNull
		@NotEmpty
		private String email;
		
		@Column(name = "token", 
				nullable = false, length = 32)
		@NotNull
		@NotEmpty
		private String token;

		@Transient
		private String password;

		public Tokens() {
			super();
		}
		
		public Tokens(String email, String token) {
			super();
			this.email = email;
			this.token = token;
			this.password = null;
		}
		
		public Tokens(String email, String token, String password) {
			super();
			this.email = email;
			this.token = token;
			
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			this.password = passwordEncoder.encode(password);
		}
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getPassword() {
			return this.password;
		}

	}
