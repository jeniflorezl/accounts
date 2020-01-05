package domain.model

sealed trait AccountStatus

case object Active extends AccountStatus
case object Inactive extends AccountStatus
case object Freezed extends AccountStatus
