Problem 1:

adt UglyArray
	uses Any, Integer, Boolean
	defines Array<T: Any>
	operations
		new: Integer -/-> Array<T>
		get: Array<T> x Integer -/-> T
		put: Array<T> x Integer x T -/-> Array<T>
		length: Array<T> ---> Integer
		empty: Array<T> x Integer -/-> Boolean
	preconditions
		new(n): 0 < n
		get(a, i): 0 <= i < length(a)
		put(a, i, t): 0 <= i < length(a)
		empty(a, i): 0 <= i < length(a)
	axioms
		get(new(n), i) = None
		empty(new(n), i) = True
		get(put(a, i, t), j) = (if i = j, then t, else get(a, j))
		length(new(n, t)) = n
		length(put(a, i, t)) = length(a)

The most basic difference between UglyArray and Array are that there is no default value for the elements of the array. Instead of returning a default value, trying to retrieve a value from an array at a position that is not set will return "None"
