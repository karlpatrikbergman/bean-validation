package se.patrikbergman.java.ee.validation;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BandValidationTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void bandNameIsNull() {
		final Band band = new Band.Builder()
				.name(null)
				.description(RandomStringUtils.randomAlphabetic(100))
				.rockFactor(2)
				.build();
		Set<ConstraintViolation<Band>> constraintViolations = validator.validate(band);
		assertEquals(1, constraintViolations.size());
		assertEquals("The band name cannot be null", constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void bandNameIsToShort() {
		final Band band = new Band.Builder()
				.name("a")
				.description(RandomStringUtils.randomAlphabetic(100))
				.rockFactor(2)
				.build();
		Set<ConstraintViolation<Band>> constraintViolations = validator.validate(band);
		assertEquals(1, constraintViolations.size());
		assertEquals("The band name size must be between 2 and 50 characters long", constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void bandRockFactorToLow() {
		final Band band = new Band.Builder()
				.name("Accept")
				.description(RandomStringUtils.randomAlphabetic(100))
				.rockFactor(0)
				.build();
		Set<ConstraintViolation<Band>> constraintViolations = validator.validate(band);
		assertEquals(1, constraintViolations.size());
		assertEquals("A bands rock factory cannot be less than 1", constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void everythingIsWrong() {
		final Band band = new Band.Builder()
				.name(null)
				.description(null)
				.rockFactor(0)
				.build();
		Set<ConstraintViolation<Band>> constraintViolations = validator.validate(band);
		assertEquals(3, constraintViolations.size());
		constraintViolations.forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
	}
}

