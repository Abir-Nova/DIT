* setting the configuration path we used previously:
$env:PHPRC = "$env:USERPROFILE\php-config"

* Then verify it:
php --ini
php -r "print_r(PDO::getAvailableDrivers());"

* start the server 
php -S localhost:8000