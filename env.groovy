def load(environment) {
    def f = readFile("./environments/${environment}.json")
    def capsule = new groovy.json.JsonSlurperClassic().parseText( f )
    return capsule
}
return this;
