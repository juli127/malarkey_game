# malarkey_game
Words' game when players create random sentence from diffrent sources (database, file, list). 

Result sentence is built with this schema: who?(subject) + what does?(verb) + where? + why?

For example, 'Сосед-дебошир Гоша /громко и фальшиво поет / под деревом по имени липа / потому что жизнь - борьба'
Result logs is located here: logs/debug.log

To run game you need:
- run mySQL server
- create database:
   - 'CREATE DATABASE malarkey_game;'
   - 'USE malarkey_game;'
