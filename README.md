# Bohr IO

Git stores "objects" in one large collection, named based on the (SHA1) hash of
their contents.  They reference each other based on these hashes.  Everything
in Git is an object.

Bohr IO does the same thing by using reflection to traverse the object,
serialize it into JSON, then package it up in clever ways to avoid duplicates
and automatically resolve multiple copies of the same data in an object.

