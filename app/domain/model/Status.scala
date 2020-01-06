package domain.model

sealed trait Status

case object Active extends Status
case object Inactive extends Status
case object Freezed extends Status