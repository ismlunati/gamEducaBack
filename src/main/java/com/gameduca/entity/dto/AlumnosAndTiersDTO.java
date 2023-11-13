package com.gameduca.entity.dto;

import java.util.List;
import java.util.Map;

public class AlumnosAndTiersDTO {
	
    private Map<Long, List<Long>> tiers;
   
    public AlumnosAndTiersDTO() {}
    
	public AlumnosAndTiersDTO(Map<Long, List<Long>> tiers) {
		super();
		this.tiers = tiers;
	}

	public Map<Long, List<Long>> getTiers() {
		return tiers;
	}

	public void setTiers(Map<Long, List<Long>> tiers) {
		this.tiers = tiers;
	}

    
    

}
