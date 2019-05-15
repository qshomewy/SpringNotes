package com.qs.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : qshomewy
 * @description : 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flow {

	private long id;

    private long flowId;

    private long plugId;
}
