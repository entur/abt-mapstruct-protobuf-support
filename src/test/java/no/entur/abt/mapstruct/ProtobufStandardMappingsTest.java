package no.entur.abt.mapstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import com.google.protobuf.Timestamp;

public class ProtobufStandardMappingsTest {

	ProtobufStandardMappings MAPPER = ProtobufStandardMappings.INSTANCE;

	@Test
	public void testMapLocalDateToTimestampSummertime() {
		LocalDateTime l = LocalDateTime.of(2000, 6, 1, 12, 0);

		Timestamp timestamp = MAPPER.map(l);
		Instant instant = MAPPER.mapToInstant(timestamp);

		LocalDateTime back = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		assertEquals(l, back);
	}

	@Test
	public void testMapLocalDateToTimestampWintertime() {
		LocalDateTime l = LocalDateTime.of(2000, 2, 1, 12, 0);

		Timestamp timestamp = MAPPER.map(l);
		Instant instant = MAPPER.mapToInstant(timestamp);

		LocalDateTime back = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		assertEquals(l, back);
	}
}
