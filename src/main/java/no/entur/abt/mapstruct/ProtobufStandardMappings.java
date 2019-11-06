package no.entur.abt.mapstruct;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import org.mapstruct.Mapper;

import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;

@Mapper
public interface ProtobufStandardMappings {

	default ByteString mapByteString(byte[] array) {
		return ByteString.copyFrom(array);
	}

	default byte[] mapByteString(ByteString in) {
		if (in != null && !in.isEmpty()) {
			return in.toByteArray();
		}

		return null;
	}

	default ByteString mapByteStringToString(String string) {
		return ByteString.copyFromUtf8(string);
	}

	default String mapStringToByteString(ByteString in) {
		if (in != null && !in.isEmpty()) {
			return in.toStringUtf8();
		}

		return null;
	}

	default Instant mapToInstant(Timestamp t) {
		if (t == null || t.getSeconds() == 0) {
			return null;
		}
		return Instant.ofEpochSecond(t.getSeconds(), t.getNanos());
	}

	default Long toEpochMilliseconds(Timestamp instance) {
		Instant instant = mapToInstant(instance);
		return instant == null ? null : instant.toEpochMilli();
	}

	default Timestamp map(Instant i) {
		if (i == null || i.getEpochSecond() == 0) {
			return null;
		}
		return Timestamp.newBuilder().setSeconds(i.getEpochSecond()).setNanos(i.getNano()).build();
	}

	default Timestamp fromEpochMilliseconds(Long instance) {
		if (instance == null) {
			return null;
		}
		Instant instant = Instant.ofEpochMilli(instance);
		return map(instant);
	}

	default Duration mapDuration(com.google.protobuf.Duration t) {
		return Duration.ofSeconds(t.getSeconds(), t.getNanos());
	}

	default com.google.protobuf.Duration map(Duration t) {
		return com.google.protobuf.Duration.newBuilder().setSeconds(t.toSeconds()).setNanos(t.toSecondsPart()).build();
	}

	default com.google.type.Date map(LocalDate t) {
		return com.google.type.Date.newBuilder().setYear(t.getYear()).setMonth(t.getMonthValue()).setDay(t.getDayOfMonth()).build();
	}

	default LocalDate mapDate(com.google.type.Date t) {
		return LocalDate.of(t.getYear(), t.getMonth(), t.getDay());
	}

	default com.google.type.TimeOfDay map(LocalTime t) {
		return com.google.type.TimeOfDay.newBuilder().setHours(t.getHour()).setMinutes(t.getMinute()).setSeconds(t.getSecond()).setNanos(t.getNano()).build();
	}

	default LocalTime mapTimeOfDay(com.google.type.TimeOfDay t) {
		return LocalTime.of(t.getHours(), t.getMinutes(), t.getSeconds(), t.getNanos());
	}

}
