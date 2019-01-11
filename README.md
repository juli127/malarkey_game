# malarkey_game  (IO/NIO, JDBC, multithreding)
- It's words' game when players create sentence from diffrent sources (database, file, list) with random words. 

- Result sentence is built with this schema: who? + what does? + where? + why?

For example, 

'Сосед-дебошир Гоша/громко и фальшиво поет/под деревом по имени липа/потому что жизнь - борьба'

'МарьИванна/слоняется без дела/на курсах кройки и шитья/потому что может себе это позволить'

Logs with result sentences are located here: logs/debug.log
Application screenshot with result sentences is located here: resources/App_screenshot.png

- Source files (resources/nouns.txt, verbs.txt, where.txt, why.txt) can be edited manually.

- To run game you need:
   - run mySQL server
   - create and use database: 'CREATE DATABASE malarkey_game;'    'USE malarkey_game;'
