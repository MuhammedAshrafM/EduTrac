package com.invade.core.network.base

abstract class Mapper<in I, out O> {
    abstract fun map(input: I): O
}
