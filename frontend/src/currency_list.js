const fs = require('fs');

fs.readFile('currencies.json', 'utf8', (err, data) => {
    if (err) {
        console.error('Error reading the JSON file:', err);
        return;
    }
    try {
        const jsonData = JSON.parse(data);

        if (jsonData.rates) {
            const currencies = Object.keys(jsonData.rates);
            console.log('List of Currencies:');
            currencies.forEach((currency) => {
                console.log(currency);
            });
        } else {
            console.error('The JSON data does not contain a "rates" property.');
        }
    } catch (error) {
        console.error('Error parsing JSON:', error);
    }
});
