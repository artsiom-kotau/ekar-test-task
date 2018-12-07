# ekar-test-task
Test task for ekar

# API
## Configure numbers of producers and consumers
POST /adjust/client

Body
{
  "consumer" : `<consumer number>`,
  "producer" : `<producer number>`
}

## Configure value
POST /adjust/counter/`<new_counter_value>`


# Startup parameters

The following parameters could be configured before startup. This parameters passed as JVM parameters.
1. spring.datasource.url - url to db with timezone. The default is `jdbc:mysql://localhost/ekar?serverTimezone=UTC`
2. spring.datasource.username - db username. The default value `root`
3. spring.datasource.password - db username password. The default value `root`
4. min.value - min value of shared number. The default value `0`
5. max.value - max value of shared number. The default value `100`
5. init.value - init value of shared number. The default value `50`
