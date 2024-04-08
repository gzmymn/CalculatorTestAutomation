package Base;

public class BaseWait extends BaseInitial {

    /** A static wait method for a specified period of seconds. Logs can be shut off.*/
    public void waitForSeconds(long duration, boolean logRequested) {
        if(logRequested)
            log.info("Waiting for " + duration + " seconds...");

        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            log.error("waitForSeconds method failure!", e);
        }

        if(logRequested)
            log.info("Waited for " + duration + " seconds.");
    }

    /** waitForSeconds method that logs are enabled by default. */
    public void waitForSeconds(long duration) {
        waitForSeconds(duration, true);
    }

    /** A static wait method for a specified period of milliseconds. Logs can be shut off. */
    public void waitForMilliseconds(long duration, boolean logRequested) {
        if(logRequested)
            log.info("Waiting for " + duration + " milliseconds...");

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            log.error("waitForMilliseconds method failure!", e);
        }

        if(logRequested)
            log.info("Waited for " + duration + " milliseconds.");
    }

    /** waitForMilliseconds method that logs are enabled by default. */
    public void waitForMilliseconds(long duration) {
        waitForMilliseconds(duration, true);
    }
}
