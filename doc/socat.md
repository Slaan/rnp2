# POP3 with socat
POP3 is a well known protocol for email transfert. The command `socat`, availiable for linux and windows, allows you to send direct packages, depending on your specified protocol. 

Usually email transfert should be encrypted. Therefore we specify `OPENSSL:ip:port`.

On arch linux you get socat by running `sudo pacman -S socat openssl`. You need `openssl` for encrytion.

Now we may connect:

```bash
socat - OPENSSL:<ip/hostnmae>:<port>
```

For example, the google POP3 domain:

```bash
 socat - OPENSSL:pop.googlemail.com:995
```
