Integer.metaClass.timesTwo = {
    return delegate * 2
}

println 5.timesTwo()
println 123.timesTwo()