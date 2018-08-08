package com.musicshop.instrument;

import java.util.List;

public interface InstrumentDao {

	List<Instrument> read(Integer familyId, Integer typeId, Integer propertyId, Integer brandId);
}
