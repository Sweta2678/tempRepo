package com.coach.middleware.batch.itemProcessor;

import org.springframework.batch.item.ItemProcessor;

import com.coach.middleware.batch.dao.MATPRICEExtractDao;
import com.coach.middleware.batch.dao.VO.MATPRICEExtractVO;


public class MATPRICEExtractItemProcessor implements ItemProcessor<MATPRICEExtractVO,MATPRICEExtractVO> {
	public MATPRICEExtractDao MATPRICEExtractDao;
	@Override
	public MATPRICEExtractVO process(MATPRICEExtractVO item) throws Exception {
	//	System.out.println("MATPRICEExtractItemProcessor "+item.getCMNUMBER());
		String cmnumber = item.getCMNUMBER();
		String currency = item.getCURRENCY();
		cmnumber = cmnumber.concat("-"+item.getCOLORCODE());
		item.setCMNUMBER(cmnumber);
		if(!currency.equals(""))
		item.setCURRENCY(currency.substring(0,3));
		return item;
	}
	public MATPRICEExtractDao getMATPRICEExtractDao() {
		return MATPRICEExtractDao;
	}
	public void setMATPRICEExtractDao(MATPRICEExtractDao mATPRICEExtractDao) {
		MATPRICEExtractDao = mATPRICEExtractDao;
	}

}
