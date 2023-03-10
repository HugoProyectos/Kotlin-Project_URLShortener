package es.unizar.urlshortener.core

class InvalidUrlException(val url: String) : Exception("[$url] does not follow a supported schema")

class RedirectionNotFound(key: String) : Exception("[$key] is not known")

class UrlNotSafeException(val url: String) : Exception("[$url] is not a safe Url")

class UrlNotReachableException(val url: String) : Exception("[$url] is not a reachable Url")

class NotValidatedYetException(val url: String) : Exception("error: Uri de destino no validada todavia")

class HashUsedException(val hash: String) : Exception("[$hash] is already mapped, so cant be mapped again")

class QRException(val hash: String) : Exception("QR [$hash] doesn't exist")

class ShowShortUrlInfoException(val id: String) : Exception("Short Url with hash [$id] doesn't exist")

class TooManyRequestsException(val id: String) : Exception("Too many requests to the Short Url with hash [$id]")
