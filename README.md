**Breakable toy, crappy code, go away ;-)**

# zazu

An unobtrusive time tracking app for Android. Zazu understands my laziness. It allows me to fill out my timesheets while walking in/out of the office.

## Idea

I need to track how much time I spent working every day. That's easy since I'm usually working full-time on a single project. However, I'm usually too lazy to open any extra app/spreadsheet when I start work or go home. And at the end of the week, my bad memory laughts at me.

It gives me notification every morning / lunchtime / evening. From the notification, I can either press "now" or enter the time I started working / headed off / duration of my lunch. At the end of the week, it emails me my hours worked per day (automatic transfer would be nice, but probably not worth the effort. Tools keep chaning. This should really focus on reminding myself.)

## Making a release

You need to generate a keystore with a key named `zazu` in it  and put it into the project folder under the name `zazu.jks`. See the (Signing your Application Guide)[https://developer.android.com/tools/publishing/app-signing.html#release-mode] for instructions.

Secrets need to be present as environment variables. Upon first use, `cp zazu.env.example zazu.env`, add your credentials, and don't check it into version control!

To generate a signed APK for release, run:

     source zazu.env
    ./gradlew assembleRelease
