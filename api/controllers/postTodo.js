const Todo = require('../model/schema')

const postTodo = (req, res) => {
    const newTodo = new Todo({
        title: req.body.title,
        description: req.body.description,
    })

    newTodo.save((err, todo) => {
        if (err) {
            res.json(err)
        }
        res.json(todo)
    })
}

module.exports = postTodo