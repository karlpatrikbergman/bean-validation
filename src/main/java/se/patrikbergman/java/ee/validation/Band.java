package se.patrikbergman.java.ee.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

class Band {
	@NotNull(message = "{band.name.null}")
	@Size(min = 2, max = 50, message = "{band.name.size}")
	private final String name;
	@NotNull
	@Size(min = 10, max = 100)
	private final String description;
	@Min(value = 1, message = "{band.rockfactor}")
	private final int rockFactor;

	private Band(Builder builder) {
		name = builder.name;
		description = builder.description;
		rockFactor = builder.rockFactor;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getRockFactor() {
		return rockFactor;
	}

	public static final class Builder {
		private String name;
		private String description;
		private int rockFactor;

		public Builder() {
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder rockFactor(int rockFactor) {
			this.rockFactor = rockFactor;
			return this;
		}

		public Band build() {
			return new Band(this);
		}
	}
}
