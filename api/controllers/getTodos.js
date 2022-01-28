const Todo = require("../model/schema");

const getTodos = (req, res) => {
  // eslint-disable-next-line array-callback-return
  Todo.find((err, todos) => {
    if (err) {
      res.json(err);
    }
    res.json(todos);
  });
};

module.exports = getTodos;
