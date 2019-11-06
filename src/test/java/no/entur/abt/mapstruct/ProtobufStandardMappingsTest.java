package no.entur.abt.mapstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

import com.google.protobuf.Timestamp;

public class ProtobufStandardMappingsTest {

	ProtobufStandardMappings MAPPER = ProtobufStandardMappings.INSTANCE;

	@Test
	public void testMapLocalDateToTimestampSummertime() {
		LocalDateTime l = LocalDateTime.of(2000, 6, 1, 12, 0);

		Timestamp timestamp = MAPPER.map(l);

		LocalDateTime back = LocalDateTime.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos(), ZoneOffset.ofHours(2));

		assertEquals(l, back);
	}

	@Test
	public void testMapLocalDateToTimestampWintertime() {
		LocalDateTime l = LocalDateTime.of(2000, 2, 1, 12, 0);

		Timestamp timestamp = MAPPER.map(l);

		LocalDateTime back = LocalDateTime.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos(), ZoneOffset.ofHours(1));

		assertEquals(l, back);
	}
}
