# Search Lang semantics


```
  p extends grammar
   := _                        # any unit

    | ... p ...                # any unit that has p in it.
                               # have to have a pair of matching ...

    | @ | in p                 # this is the context
                               # @ itself is meaningless and works 
                               # only in a context see semantics

    | ! p                      # negation
    | p | p                    # disjunction
    | p ; p                    # conjunction

    | [ p ]                    # a sequence of patterns
```

`_` matches any parseable unit

`... p ...` matches any unit that contains a unit that p matches. In other words, it matches any of `p`'s ancestors

`in c` matches any unit `u` such that `c[u/@]` has a match. In other words, it matches any unit `u` whose ancestor matches `c[u/@]`

`! p` matches any unit that `p` does not match. 

`p1 | p2` matches any unit that either `p1` or `p2` matches

`p1 ; p2` matches any unit that both `p1` and `p2` match

`[p]` matches a unit that is a sequence of units, each of which `p` matches
