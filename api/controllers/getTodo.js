const Todo = require("../model/schema")

const getTodo = (req, res) => {
    Todo.find((err, todos) => {
        if (err) {
            res.json(err)
        }
        res.json(todos)
    })
}

module.exports = getTodo