enum DayOfWeek {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday
}

def weekRange = DayOfWeek.Monday..DayOfWeek.Sunday

println "The size of the range is " + weekRange.size()
println "The range contains Wednesday is " + weekRange.contains(DayOfWeek.Wednesday)
println "The first element of the range is " + weekRange.from
println "The last element of the range is " + weekRange.to
println ""

def weekList = [
    DayOfWeek.Monday,
    DayOfWeek.Tuesday,
    DayOfWeek.Wednesday,
    DayOfWeek.Thursday,
    DayOfWeek.Friday,
    DayOfWeek.Saturday,
    DayOfWeek.Sunday
]

println weekList
println "The size of the list is " + weekList.size()
weekList.removeAt(5)
weekList.add(5, DayOfWeek.Saturday)
println "The third element of the list is " + weekList[2]
println ""

def weekMap = [
    1: "Monday",
    2: "Tuesday",
    3: "Wednesday",
    4: "Thursday",
    5: "Friday",
    6: "Saturday",
    7: "Sunday"
]

println weekMap
println "The class of the map is " + weekMap.getClass().getName()
println "The size of the map is " + weekMap.size()
println weekMap.values()