package com.paltabrain.billing.rc.mappers

import com.paltabrain.billing.mappers.Mapper
import com.revenuecat.purchases.PurchasesError as RcPurchasesError
import com.paltabrain.billing.purchases.models.PurchasesError
import com.paltabrain.billing.purchases.models.PurchasesErrorCode
import com.revenuecat.purchases.PurchasesErrorCode as RcPurchasesErrorCode

class PurchasesErrorMapper : Mapper<PurchasesError, RcPurchasesError> {

    @SuppressWarnings("ComplexMethod")
    override fun mapToEntity(data: PurchasesError): RcPurchasesError {
        val code = when (data.code) {
            PurchasesErrorCode.UnknownError -> RcPurchasesErrorCode.UnknownError
            PurchasesErrorCode.PurchaseCancelledError -> RcPurchasesErrorCode.PurchaseCancelledError
            PurchasesErrorCode.StoreProblemError -> RcPurchasesErrorCode.StoreProblemError
            PurchasesErrorCode.PurchaseNotAllowedError -> RcPurchasesErrorCode.PurchaseNotAllowedError
            PurchasesErrorCode.PurchaseInvalidError -> RcPurchasesErrorCode.PurchaseInvalidError
            PurchasesErrorCode.ProductNotAvailableForPurchaseError -> RcPurchasesErrorCode.ProductNotAvailableForPurchaseError
            PurchasesErrorCode.ProductAlreadyPurchasedError -> RcPurchasesErrorCode.ProductAlreadyPurchasedError
            PurchasesErrorCode.ReceiptAlreadyInUseError -> RcPurchasesErrorCode.ReceiptAlreadyInUseError
            PurchasesErrorCode.InvalidReceiptError -> RcPurchasesErrorCode.InvalidReceiptError
            PurchasesErrorCode.MissingReceiptFileError -> RcPurchasesErrorCode.MissingReceiptFileError
            PurchasesErrorCode.NetworkError -> RcPurchasesErrorCode.NetworkError
            PurchasesErrorCode.InvalidCredentialsError -> RcPurchasesErrorCode.InvalidCredentialsError
            PurchasesErrorCode.UnexpectedBackendResponseError -> RcPurchasesErrorCode.UnexpectedBackendResponseError
            PurchasesErrorCode.InvalidAppUserIdError -> RcPurchasesErrorCode.InvalidAppUserIdError
            PurchasesErrorCode.OperationAlreadyInProgressError -> RcPurchasesErrorCode.OperationAlreadyInProgressError
            PurchasesErrorCode.UnknownBackendError -> RcPurchasesErrorCode.UnknownBackendError
            PurchasesErrorCode.InvalidAppleSubscriptionKeyError -> RcPurchasesErrorCode.InvalidAppleSubscriptionKeyError
            PurchasesErrorCode.IneligibleError -> RcPurchasesErrorCode.IneligibleError
            PurchasesErrorCode.InsufficientPermissionsError -> RcPurchasesErrorCode.InsufficientPermissionsError
            PurchasesErrorCode.PaymentPendingError -> RcPurchasesErrorCode.PaymentPendingError
            PurchasesErrorCode.InvalidSubscriberAttributesError -> RcPurchasesErrorCode.InvalidSubscriberAttributesError
            PurchasesErrorCode.LogOutWithAnonymousUserError -> RcPurchasesErrorCode.LogOutWithAnonymousUserError
            PurchasesErrorCode.ConfigurationError -> RcPurchasesErrorCode.ConfigurationError
            PurchasesErrorCode.UnsupportedError -> RcPurchasesErrorCode.UnsupportedError
        }
        return RcPurchasesError(
            code = code,
            underlyingErrorMessage = data.underlyingErrorMessage,
        )
    }

    @SuppressWarnings("ComplexMethod")
    override fun mapToData(entity: RcPurchasesError): PurchasesError {
        val code = when (entity.code) {
            RcPurchasesErrorCode.UnknownError -> PurchasesErrorCode.UnknownError
            RcPurchasesErrorCode.PurchaseCancelledError -> PurchasesErrorCode.PurchaseCancelledError
            RcPurchasesErrorCode.StoreProblemError -> PurchasesErrorCode.StoreProblemError
            RcPurchasesErrorCode.PurchaseNotAllowedError -> PurchasesErrorCode.PurchaseNotAllowedError
            RcPurchasesErrorCode.PurchaseInvalidError -> PurchasesErrorCode.PurchaseInvalidError
            RcPurchasesErrorCode.ProductNotAvailableForPurchaseError -> PurchasesErrorCode.ProductNotAvailableForPurchaseError
            RcPurchasesErrorCode.ProductAlreadyPurchasedError -> PurchasesErrorCode.ProductAlreadyPurchasedError
            RcPurchasesErrorCode.ReceiptAlreadyInUseError -> PurchasesErrorCode.ReceiptAlreadyInUseError
            RcPurchasesErrorCode.InvalidReceiptError -> PurchasesErrorCode.InvalidReceiptError
            RcPurchasesErrorCode.MissingReceiptFileError -> PurchasesErrorCode.MissingReceiptFileError
            RcPurchasesErrorCode.NetworkError -> PurchasesErrorCode.NetworkError
            RcPurchasesErrorCode.InvalidCredentialsError -> PurchasesErrorCode.InvalidCredentialsError
            RcPurchasesErrorCode.UnexpectedBackendResponseError -> PurchasesErrorCode.UnexpectedBackendResponseError
            RcPurchasesErrorCode.InvalidAppUserIdError -> PurchasesErrorCode.InvalidAppUserIdError
            RcPurchasesErrorCode.OperationAlreadyInProgressError -> PurchasesErrorCode.OperationAlreadyInProgressError
            RcPurchasesErrorCode.UnknownBackendError -> PurchasesErrorCode.UnknownBackendError
            RcPurchasesErrorCode.InvalidAppleSubscriptionKeyError -> PurchasesErrorCode.InvalidAppleSubscriptionKeyError
            RcPurchasesErrorCode.IneligibleError -> PurchasesErrorCode.IneligibleError
            RcPurchasesErrorCode.InsufficientPermissionsError -> PurchasesErrorCode.InsufficientPermissionsError
            RcPurchasesErrorCode.PaymentPendingError -> PurchasesErrorCode.PaymentPendingError
            RcPurchasesErrorCode.InvalidSubscriberAttributesError -> PurchasesErrorCode.InvalidSubscriberAttributesError
            RcPurchasesErrorCode.LogOutWithAnonymousUserError -> PurchasesErrorCode.LogOutWithAnonymousUserError
            RcPurchasesErrorCode.ConfigurationError -> PurchasesErrorCode.ConfigurationError
            RcPurchasesErrorCode.UnsupportedError -> PurchasesErrorCode.UnsupportedError
        }
        return PurchasesError(
            code = code,
            underlyingErrorMessage = entity.underlyingErrorMessage,
        )
    }
}
