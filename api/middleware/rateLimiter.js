const rateLimit = require("express-rate-limit");

const rateLimiter = rateLimit({
  windowMs: 1 * 60 * 1000,
  max: 10,
  message: `Uh uh... The rate limit hammer has spoken`,
});

module.exports = rateLimiter;
