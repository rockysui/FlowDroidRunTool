import sqlite3
import os

sqlFileName = "results.db"
sqlConn = sqlite3.connect(sqlFileName)

optionFiles = {}

# Clears all tables in the database first
sqlCursor = sqlConn.cursor()
sqlCursor.execute("DELETE FROM Application")
sqlCursor.execute("DELETE FROM ApplicationResults")
sqlConn.commit()

for propFile in os.listdir('.'):
    propFileName, propFileExt = os.path.splitext(propFile)
    if propFileExt == ".properties":
        fileSplit = propFileName.split('_')
        appNum = int(fileSplit[0]) # Guarenteed to work under these conditions
        if appNum not in optionFiles.keys():
            optionFiles[appNum] = []

            # Works under these specific conditions
            appNameSplit = propFileName.split('_')
            appName = appNameSplit[0] + "_" + appNameSplit[1]

            sqlCursor.execute("INSERT INTO Application (appNum, appName) VALUES (?, ?)", [appNum, appName])
            sqlConn.commit()

        optionFiles[appNum].append(propFile)
        
for appNum in optionFiles:
    print (appNum)
    appNumResults = optionFiles[appNum]
    finishedLevel = 0
    
    for appResultFile in appNumResults:
        appResultCompletedFile = appResultFile + "complete"

        RAM = None
        RAMLevel = None
        timeToRun = None

        completed = False
        if (os.path.isfile(appResultCompletedFile)):
            completedFileHandle = open(appResultCompletedFile)
            completedLines = completedFileHandle.readlines()

            if len(completedLines) > 0:
                
                RAM = float(completedLines[1][len("ram usage:"):-1])
                timeToRun = float(completedLines[2][len("time:"):-1])
                completed = True
            
        
        resultFile = open(appResultFile)
        lines = resultFile.readlines()
        if len(lines) > 0:
            
            optionName = lines[0].rstrip()
            callGraphEdge = int(lines[1][len("callgraphedges:"):-1])
            sinks = int(lines[2][len("sinks:"):-1])
            sources = int(lines[3][len("sources:"):-1])
            entrypoints = int(lines[4][len("entrypoints:"):-1])
            reachablemethods = int(lines[5][len("reachablemethods:"):-1])
            numclasses = int(lines[6][len("numclasses:"):-1])
            providers = int(lines[7][len("providers:"):-1])
            services = int(lines[8][len("services:"):-1])
            activities = int(lines[9][len("activities:"):-1])
            receivers = int(lines[10][len("receivers:"):-1])

            thisLevel = [int(s) for s in optionName.split() if s.isdigit()][0]
            
            sqlCursor.execute("""INSERT INTO ApplicationResults (optionName, optionLevel, appNum, RAM, timeToRun, completed, callGraphEdges, numSources, numSinks, numEntrypoints, numReachableMethods, numClasses, providers, services, activities, receivers)
                                 VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""", [optionName, thisLevel, appNum, RAM, timeToRun, completed, callGraphEdge, sources, sinks, entrypoints, reachablemethods, numclasses, providers, services, activities, receivers])
            
            
            
            if (thisLevel < finishedLevel or finishedLevel == 0) and completed == True:
                finishedLevel = thisLevel
        

        sqlConn.commit()

        resultFile.close()
        
    sqlCursor.execute('UPDATE Application SET finishedLevel=? WHERE appNum=?', [finishedLevel, appNum])
    sqlConn.commit()

