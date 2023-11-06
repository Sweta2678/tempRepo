package com.coach.middleware.batch.dao.VO;

import java.util.Date;

public class CjiPriceMOAVO {

		public Double CJIPRICE;
		public String STYLENUMBER;
		public String SKU;
		public String OWNERUNIQUEID;
		public String OWNER;
		private Date CJISTARTDATE;
		private Date CJIENDDATE;
		public Double getCJIPRICE() {
			return CJIPRICE;
		}

		public void setCJIPRICE(Double cJIPRICE) {
			CJIPRICE = cJIPRICE;
		}

		public String getOWNERUNIQUEID() {
			return OWNERUNIQUEID;
		}

		public void setOWNERUNIQUEID(String oWNERUNIQUEID) {
			OWNERUNIQUEID = oWNERUNIQUEID;
		}

		public String getOWNER() {
			return OWNER;
		}

		public void setOWNER(String oWNER) {
			OWNER = oWNER;
		}

		public String getSTYLENUMBER() {
			return STYLENUMBER;
		}

		public void setSTYLENUMBER(String sTYLENUMBER) {
			STYLENUMBER = sTYLENUMBER;
		}

		public String getSKU() {
			return SKU;
		}

		public void setSKU(String sKU) {
			SKU = sKU;
		}

		public Date getCJISTARTDATE() {
			return CJISTARTDATE;
		}

		public void setCJISTARTDATE(Date cJISTARTDATE) {
			CJISTARTDATE = cJISTARTDATE;
		}

		public Date getCJIENDDATE() {
			return CJIENDDATE;
		}

		public void setCJIENDDATE(Date cJIENDDATE) {
			CJIENDDATE = cJIENDDATE;
		}
		
	}
