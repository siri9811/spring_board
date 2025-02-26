package spring.community.exception;

abstract class DomainException extends RuntimeException {

}

abstract class NotFountException extends DomainException {

}

abstract class ForbiddenException extends DomainException {

}

abstract class ConflictException extends DomainException {

}

abstract class BadRequestException extends DomainException {

}

