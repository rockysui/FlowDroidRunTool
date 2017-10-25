create table Application (
    appNum integer primary key,
    appName char(64),
    finishedLevel integer
);

create table ApplicationResults (
    appNum integer,
    optionName char(64),
    optionLevel integer,
    RAM double,
    timeToRun double,
    completed boolean not null,
    callGraphEdges integer,
    numSources integer,
    numSinks integer,
    numEntrypoints integer,
    numReachableMethods integer, 
    numClasses integer,
    providers integer,
    services integer,
    activities integer,
    receivers integer,
    foreign key(appNum) references Application(appNum)
);