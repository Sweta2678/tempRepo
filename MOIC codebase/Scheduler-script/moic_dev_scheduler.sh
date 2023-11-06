#!/bin/sh

schedulerName="${1}"
case ${schedulerName} in
	"APP_MOIC_CSTMR_MSTR_STG")
		echo "processCustomerMasterStaging job is running ..."
		curl https://usawidlcweapp02.global.coach.com:8443/Moic-api-0.0.1/Moic/api/processCustomerMasterStaging
		echo
		;;
	"APP_MOIC_CSTMR_MSTR_JSN")
		echo "processCustomerMasterJson job is running ..."
		curl https://usawidlcweapp02.global.coach.com:8443/Moic-api-0.0.1/Moic/api/processCustomerMasterJson
		echo
		;;
	"APP_MOIC_PRDCT_MSTR_STG")
		echo "processProductMasterStaging job is running ..."
		curl https://usawidlcweapp02.global.coach.com:8443/Moic-api-0.0.1/Moic/api/processProductMasterStaging
		echo
		;;
	"APP_MOIC_JOOR_ORDR_JSN")
		echo "processJoorOrderJson job is running ..."
		curl https://usawidlcweapp02.global.coach.com:8443/Moic-api-0.0.1/Moic/api/processJoorOrderJson
		echo
		;;
	*)
		echo "`basename ${0}`: invalid parameter passed, please enter valid scheduler name"
		exit 1
		;;
esac
