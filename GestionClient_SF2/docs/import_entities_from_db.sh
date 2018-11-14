#!/bin/bash

php app/console doctrine:mapping:convert xml ./src/NMC/GestionClient/SF2/SimpleBundle/Resources/config/doctrine/metadata/orm --from-database --force
php app/console doctrine:mapping:import NMCGestionClientSF2SimpleBundle annotation
php app/console doctrine:generate:entities NMCGestionClientSF2SimpleBundle