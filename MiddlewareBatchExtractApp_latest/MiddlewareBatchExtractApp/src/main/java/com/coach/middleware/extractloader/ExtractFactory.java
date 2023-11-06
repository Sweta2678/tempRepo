package com.coach.middleware.extractloader;

import com.coach.middleware.extractloader.Exceptions.CoachExtractException;

public class ExtractFactory implements Cloneable {

	public static ExtractFactory extractFactory = null;

	private ExtractFactory() {

	}

	public static ExtractFactory getExtractFactoy() {
		synchronized (ExtractFactory.class) {
			if (extractFactory == null) {
				extractFactory = new ExtractFactory();
			}
			return extractFactory;
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException(
				"ExtractFactory is a Singletonclass class cant able to clone");
	}

	public IExtractLoader getExtract(String name) throws CoachExtractException {
		IExtractLoader loader = null;
		if (name == null) {
			throw new CoachExtractException("COACH002");
		}
		if (name.equalsIgnoreCase(IExtractLoader.LPSKUExtract)) {
			loader = new LpskuExtract();
		} else if (name.equalsIgnoreCase(IExtractLoader.LPPRICESExtract)) {
			loader = new LPPRICESExtract();
		} else if (name.equalsIgnoreCase(IExtractLoader.TOTOExtract)) {
			loader = new TOTOExtract();
		} else if (name.equalsIgnoreCase(IExtractLoader.MATMSTRXExtract)) {
			loader = new MATMSTRXExtract();
		} else if (name.equalsIgnoreCase(IExtractLoader.MATPRICEExtract)) {
			loader = new MATPRICEExtract();
		}
		else if (name.equalsIgnoreCase(IExtractLoader.PRODMASTExtract)) {
			loader = new PRODMASTExtract();

		}else if (name.equalsIgnoreCase(IExtractLoader.RPFTPABCExtract)) {
			loader = new RPFTPABCExtract();

		}else if (name.equalsIgnoreCase(IExtractLoader.SAPMATLPExtract)) {
			loader = new SAPMATLPExtract();

		} else if (name.equalsIgnoreCase(IExtractLoader.SAPMCOMPLPExtract)) {
			loader = new SAPMCOMPLPExtract();

		} else if (name.equalsIgnoreCase(IExtractLoader.LPSKUSAPExtract)) {
			loader = new LPSKUSAPExtract();

		}else if (name.equalsIgnoreCase(IExtractLoader.ESSSTYLECOLORExtract)) {
			loader = new ESSSTYLECOLORExtract();

		}else if (name.equalsIgnoreCase(IExtractLoader.ESSSTYLEExtract)) {
			loader = new ESSSTYLEExtract();

		}else if (name.equalsIgnoreCase(IExtractLoader.ESSSTYLECOLORExtract)) {
			loader = new ESSSTYLECOLORExtract();

		}else if (name.equalsIgnoreCase(IExtractLoader.ESSSTYLEExtract)) {
			loader = new ESSSTYLEExtract();

		}else if (name.equalsIgnoreCase(IExtractLoader.BATCHMETADATACONFIG)) {
			loader = new BatchMetadataConfigJob();

		}else if (name.equalsIgnoreCase(IExtractLoader.DELTAEXTRACT)) {
			loader = new DeltaExtract();

		} else if (name.equalsIgnoreCase(IExtractLoader.PRODMASTDWExtract)) {
			loader = new ProdmastDWExtract();

		} 
		else if (name.equalsIgnoreCase(IExtractLoader.PRODUCTRENAMEExtract)) {
			loader = new ProductRenameExtract();

		}  else {
			throw new CoachExtractException("COACH001");
		}

		return loader;
	}

};
