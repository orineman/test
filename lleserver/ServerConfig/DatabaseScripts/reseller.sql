INSERT INTO reseller VALUES ('lle','laplink','laplink','www.ll2go.com','http://esd.element5.com/product.html?productid=532564&languageid=1&backlink=http%3A%2F%2Fwww.ll2go.com&noquickbuy=1&currencies=USD','en',4,'4,4,4',15,'com.infowave.c4p.laplink.security.PasswordEncoderImpl');


-- param1: resellerid - just an internal value - can be anything - must be unique
-- param2: resellercode - lookup code for this reseller - a corresponding folder in /c4/system/portal/resources must exist with reseller specific resources.
-- param3: resellername - the name of this reseller
-- param4: hostname - comma separated values of the hostname,ip's that this reseller will respond to
-- param5: expiryurl - this is the url of the purchasing site for obtaining licenses
-- param6: locale - default to 'en'
-- param7: # of security questions - match this up with the questions in the reseller's properties file
-- param8: format for license code - '4,4,4' means code format of xxxx-xxxx-xxxx
-- param9: demo duration - new accounts will have a demo license of x days
-- param10: leave as is - 'com.infowave.c4p.laplink.security.PasswordEncoderImpl'