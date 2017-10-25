import sqlite3

sqlFileName = "results.db"
sqlConn = sqlite3.connect(sqlFileName)

sqlCursor = sqlConn.cursor()
sqlCursor.execute("""SELECT completed, callGraphEdges, numSources, numSinks, numEntrypoints, numReachableMethods, numClasses, providers, services, activities, receivers, MIN(optionLevel) FROM ApplicationResults
                   GROUP BY completed, callGraphEdges, numSources, numSinks, numEntrypoints, numReachableMethods, numClasses, providers, services, activities, receivers;""")

appResults = sqlCursor.fetchall()

dataFile = open('data.txt', 'w+')
for result in appResults:
    completed = result[0]
    cge = result[1]
    sources = result[2]
    sinks = result[3]
    entrypoints = result[4]
    reachablemethods = result[5]
    numclasses = result[6]
    providers = result[7]
    services = result[8]
    activities = result[9]
    receivers = result[10]
    optionLevel = result[11]

    if completed == 1:
        dataStr = '%d 0:%d 1:%d 2:%d 3:%d 4:%d 5:%d 6:%d 7:%d 8:%d 9:%d\n' % (optionLevel, cge, sources, sinks, entrypoints, reachablemethods, numclasses, providers, services, activities, receivers)
        dataFile.write(dataStr)

    
dataFile.close()
print ("Done!")
