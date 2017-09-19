Problem 1:

adt UglyArray
	uses Any, Integer, Boolean
	defines UglyArray<T: Any>
	operations
		new: Integer -/-> UglyArray<T>
		get: UglyArray<T> x Integer -/-> T
		put: UglyArray<T> x Integer x T -/-> UglyArray<T>
		length: UglyArray<T> ---> Integer
		empty: UglyArray<T> x Integer -/-> Boolean
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

Problem 2:

adt FlexibleArray
	uses Any, Integer
	defines FlexibleArray<T: Any>
	operations: 
		new: Integer x Integer x T -/> FlexibleArray<T>
		get: FlexibleArray<T> x Integer -/-> T
		put: FlexibleArray<T> x Integer x T -/-> T
		length: FlexibleArray<T> ---> T
		lower: FlexibleArray<T> ---> Integer
	predconditions
		new(m, n, t): m <= n
		get(a, i): lower(a) <= i < lower(a) + length(a)
		put(a, i, t): lower(a) <= i < lower(a) + length(a)
	axioms
		get(new(m, n, t), i) = t
		get(put(a, i, t), j) = (if i = j then t else get(a, j))
		length(new(m, n, t)) = n - m + 1
		lower(new(m, n, t)) = m
		length(put(a, i, t)) = length(a)



