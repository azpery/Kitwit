function Interval(callback, delay) {
    var timerId, start, remaining = delay;

    this.pause = function() {
        window.clearInterval(timerId);
        remaining -= new Date() - start;
    };

    this.resume = function() {
        start = new Date();
        window.clearInterval(timerId);
        timerId = window.setInterval(callback, remaining);
    };

    this.resume();
}